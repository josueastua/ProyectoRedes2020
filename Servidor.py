import socket
from random import randint
import threading
from time import sleep
from os import system

#Maneja el turno actual
var_turno = 1
#Una lista con los jugadores conectos
var_jugadores = []
# Representacion de las cartas
var_cartas = [
#Cartas de organos
"1:0:1", "1:0:1", "1:0:1", "1:0:1", "1:0:1", "1:0:2", "1:0:2", "1:0:2", "1:0:2", "1:0:2",
"1:0:3", "1:0:3", "1:0:3", "1:0:3", "1:0:3", "1:0:4", "1:0:4", "1:0:4", "1:0:4", "1:0:4", 
"1:0:5", 
#Cartas de medicina
"2:1:1", "2:2:1", "2:3:1", "2:4:1", "2:1:2", "2:2:2", "2:3:2", "2:4:2", "2:1:3",  "2:2:3", 
"2:3:3", "2:4:3", "2:1:4", "2:2:4", "2:3:4", "2:4:4", "2:5:5", "2:5:5", "2:5:5", "2:5:5",
#Cartas de virus
"3:1:1", "3:2:1", "3:3:1", "3:4:1", "3:1:2", "3:2:2", "3:3:2", "3:4:2", "3:1:3", "3:2:3", 
"3:3:3", "3:4:3", "3:1:4", "3:2:4", "3:3:4", "3:4:4", "3:5:5", 
#Cartas especiales
"4:0:1", "4:0:2", "4:0:3", "4:0:4", "4:0:5"
]
#Aqui se guardaran las cartas pero mezcladas
var_mazo = []
#Una variable que almacena una cadena de string codificada que representa el juego
var_datosJuego = ""
#Este hilo una vez se haya conectado 3 se esperara una x cantidad de tiempo y se iniciara
var_hilo = None
#Para que no puedan conectarse mas jugadores despues de iniciado el juego
var_juegoIniciado = False
#Para saber si un jugador salio
var_jugadorSalio = False
#id del jugador que migro
var_idSalio = ""

class Jugador:
    def __init__(self, id, nick):
        self.id = id
        self.nick = nick
        self.mano = []

    def addMano(self, carta):
        self.mano.append(carta)
    
    def toString(self):
        aux = str(self.id)+"_"+self.nick
        for a in self.mano:
            aux += "_"+a
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
    for a in range(len(var_cartas)):
        asignados.append(False)
    while(todosUsados(asignados)):
        aux = randint(0, len(asignados)-1)
        print(aux, str(len(asignados)), sep = " - ")
        if(asignados[aux] == False):
            var_mazo.append(var_cartas[aux])
            asignados[aux] = True

def metodoHilo():
    esperaInicio()

def esperaInicio():
    print("Inicio Espera")
    global var_hilo, var_juegoIniciado
    cont = 0
    while(cont < 30):
        sleep(1)
        cont += 1
    var_juegoIniciado = True
    try:
        var_hilo = None
    except ValueError:
        print("Error cerrando el hilo")

def infJugadoresYMazo():
    datos = []
    mezclarBaraja()
    for a in var_jugadores:
        a.addMano(var_mazo.pop(0))
        a.addMano(var_mazo.pop(0))
        a.addMano(var_mazo.pop(0))
        datos.append(a.toString())
    aux = ""
    for a in var_mazo:
        aux += "_"+a
    aux = aux[1: len(aux)]
    datos.append(aux)
    aux = ""
    for a in datos:
        aux += "/"+a
    aux = aux[1: len(aux)]
    return aux

def procesarSolicitud(clave, mensaje, hostname):
    global var_turno, var_datosJuego, var_juegoIniciado, var_jugadorSalio, var_idSalio, var_hilo
    if(clave == "1"):#Solicitud de conectarse
        if(var_juegoIniciado is False):
            id = generarId()
            var_jugadores.append(Jugador(id, mensaje))
            if(len(var_jugadores) >= 2 and var_hilo is None):
                var_hilo = threading.Thread(target=metodoHilo) 
                var_hilo.start()
            return "1:"+str(id)+":"+str(len(var_jugadores))
        else:
            return "Conexion rechazada: Ya ha iniciado una partida"
    elif(clave == "2"):#Solicita la cantidad de jugadores conectados
        if(var_juegoIniciado is False):
            return "2:"+str(len(var_jugadores))
        else:
            return "3:"+infJugadoresYMazo()
    elif(clave == "3"):#Para cuando un jugador hace una jugada
        var_datosJuego = mensaje
        if(var_turno == len(var_jugadores)):
            var_turno = 1
        else:
            var_turno += 1
        return "Se estableció conexión con: "+hostname
    elif(clave == "4"):#pedir actualizacion de los datos del juego
        return "4:"+str(var_turno)+":"+var_datosJuego+":"+str(var_jugadorSalio)+":"+var_idSalio
    elif(clave == "5"):
        var_jugadorSalio = True
        var_idSalio = mensaje
        index = 0
        for a in range(len(var_jugadores)):
            if(var_jugadores[a] == mensaje):
                index = a
        var_jugadores.pop(index)
        return "Se estableció conexión con: "+hostname

def iniciarServidor(host,puerto):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((host,puerto))
    s.listen(5)
    system("cls")
    while True:
        (c, addr) = s.accept()
        print("Se estableció conexión con: " + str(addr))
        hostname = socket.gethostname()
        msg_rec = c.recv(1024)
        msg_rec = msg_rec.decode('ascii')
        msg_env = procesarSolicitud(msg_rec[0], msg_rec, hostname)
        print(msg_env)
        c.send(msg_env.encode('utf8'))
        print("\nMensaje decodificado: "+msg_rec+"\n")
        c.close()

if __name__ == "__main__":
    iniciarServidor("", 44440)