import socket
from random import randint
import threading
from time import sleep

turno = 1
jugadores = []
cartas = [
"1:0:1", "1:0:1", "1:0:1", "1:0:1", "1:0:1", "1:0:2", "1:0:2", "1:0:2", "1:0:2", "1:0:2",
"1:0:3", "1:0:3", "1:0:3", "1:0:3", "1:0:3", "1:0:4", "1:0:4", "1:0:4", "1:0:4", "1:0:4", 
"1:0:5", "2:1:1", "2:2:1", "2:3:1", "2:4:1", "2:1:2", "2:2:2", "2:3:2", "2:4:2", "2:1:3", 
"2:2:3", "2:3:3", "2:4:3", "2:1:4", "2:2:4", "2:3:4", "2:4:4", "2:5:5", "3:1:1", "3:2:1", 
"3:3:1", "3:4:1", "3:1:2", "3:2:2", "3:3:2", "3:4:2", "3:1:3", "3:2:3", "3:3:3", "3:4:3", 
"3:1:4", "3:2:4", "3:3:4", "3:4:4", "3:5:5", "4:0:1", "4:0:2", "4:0:3", "4:0:4", "4:0:5"
]
mazo = []
datosJuego = []
hilo = None
juegoiniciado = False

class Jugador:
    def __init__(self, id, nombre):
        self.id = id
        self.nombre = nombre
        self.mano = []
 
    def addMano(self, carta):
        self.mano.append(carta)

    def toString(self):
        aux = str(id)+":"+nombre
        for a in mano:
            aux += ":"+a
        return aux

def generarId():
    id = 0
    aux = 0
    aux = str(randint(1, 9))
    for a in range(8):
        aux += str(randint(0, 9))
    id = int(aux)
    return id

def todosUsados(lista):
    for a in range(len(lista)):
        if(lista[a] == False):
            return True
    return False

def mezclarBaraja():
    asignados = []
    for a in range(len(cartas)):
        asignados[a] = False
    while(todosUsados(asignados)):
        aux = randint(0, len(asignados))
        if(asignados[aux] == False):
            mazo.append(cartas[aux])
            asignados[aux] = True
    

def initCliente(host, puerto, msg_env):
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
    while True:
        (c, addr) = s.accept()
        print("Se estableció conexión con: " + str(addr))
        ip = socket.gethostbyname(socket.gethostname())
        hostname = socket.gethostname()
        msg_rec = c.recv(1024)
        msg_rec = msg_rec.decode('ascii')
        c.send(procesarSolicitud(msg_rec[0], msg_rec, hostname).encode('utf8'))
        print("\nMensaje decodificado: "+msg_rec+"\n")
        c.close()
        enviarRespuesta(msg_rec[0], ip, hostname)

def procesarSolicitud(clave, men_rec, ip, hostname):
    global turno, juegoiniciado
    if(clave == '1'):#1 es conectarse
        if(juegoiniciado is False):
            id = generarId()
            jugadores[ip] = Jugador(id, men_rec)
            return "1"+str(id)+":"+str(len(jugadores))
        else:
            return "Ya inicio una partida"
    elif(clave == '2'):#cuando se hace una jugada
        datosJuego.append(men_rec)
        if(men_rec == "000000"):
            if(turno == len(jugadores)):
                turno = 1
            else:
                turno += 1
            return "2"+str(turno)
        else:
            return "Conexion establecida con: "+hostname
    elif(clave == '3'):
        jugadores.pop(ip)
        return "Conexion establecida con: "+hostname


def enviarRespuesta(clave, ip):
    if(clave == '1'):#responder a solicitud de conexion
        global hilo
        for key in jugadores:
            initCliente(key, 44440, "3"+str(len(jugadores)))
        if(len(jugadores) >= 3 and hilo is None):
            hilo = threading.Thread(target=esperaInicio)
            hilo.start()
    elif(clave == '2'):
        for key in jugadores:
            if(key != ip):
                for dato in datosJuego:
                    initCliente(key, 44440, "4"+dato)

def esperaInicio():
    global hilo
    datos = []
    cont = 0
    while(cont < 30):
        sleep(1)
        cont += 1
    for a in jugadores:
        jugadores[a].addMano(mazo.pop(0))
        jugadores[a].addMano(mazo.pop(0))
        jugadores[a].addMano(mazo.pop(0))
        datos.append("4"+jugadores[a].toString())
    aux = ""
    for a in mazo:
        aux += a + "-"
    aux = aux[0:len(aux)-1]
    datos.append("4"+aux)
    datos.append("4000000")
    for a in jugadores:
        for dato in datos:
            initCliente(a, 44440, dato)
    hilo._stop()
    

if __name__ == "__main__":
    print(str(cartas), sep="-")