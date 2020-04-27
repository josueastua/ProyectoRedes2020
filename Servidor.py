import socket
from os import system
from time import sleep

id = 0
turno = 1
jugadores = list()
actualizacion = ""

class Jugador:
    def __init__(self, id, nombre):
        self.id = id
        self.nombre = nombre

def iniciarCliente(host, puerto, msg_env):
    c = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    c.connect((host,puerto))
    msg_rec = c.recv(1024)
    print(msg_rec.decode('utf8'))
    c.send(msg_env.encode('ascii'))
    c.close()

def iniciarServidor(host,puerto):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((host,puerto))
    s.listen(5)
    system("cls")
    while True:
        (c, addr) = s.accept()
        print("Se estableció conexión con: " + str(addr))
        msg_rec = c.recv(1024)
        msg_rec = msg_rec.decode('ascii')
        c.send(procesarSolicitud(msg_rec[0], msg_rec, socket.gethostname()).encode('utf8'))
        print("\nMensaje decodificado: "+msg_rec+"\n")
        c.close()
        
def procesarSolicitud(clave, men_rec, hostname):
    if(clave == 'C'):
        global id
        global turno
        id += 1
        jugadores.append(Jugador(id, hostname))
        return str(id)+str(turno)
    elif(clave == 'J'):
        return str(len(jugadores))
    elif(clave == 'A'):
        global actualizacion
        actualizacion = men_rec
        turno += 1
        actualizacion = str(turno) + str(len(jugadores)) + actualizacion
        return str(turno)
    elif(clave == 'I'):
        return actualizacion
    elif(clave == "S"):
        borrarJugador(int(men_rec[1]))
        return "Conexion establecida con: "+hostname;
        
def borrarJugador(id):
    player = None
    if(jugadores is not None):
        for a in jugadores:
            if(a.id == id):
                jugadores.remove(a)
                break
                

if __name__ == "__main__":
    host = ""
    puerto = 44440
    iniciarServidor(host,puerto)
