import socket
from Transporte import Transporte
from Presentacion import Presentacion


class Sesion(object):
    def __init__(self):
        self.conexion = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.ipdestino

    def setIpdestino(self, ipdestino):
        self.ipdestino = ipdestino

    def modoCliente(self, mensaje):
        self.conexion.connect((self.ipdestino, 44440))
        msg_rec = self.conexion.recv(1024)
        print(msg_rec.decode('utf8'))
        self.conexion.send(mensaje.encode('ascii'))
        self.conexion.close()

    def modoServidor(self):
        self.conexion.bind(("",44440))
        self.conexion.listen(5)
        while True:
            (c, addr) = self.conexion.accept()
            print("Se estableció conexión con: " + str(addr))
            msg_rec = c.recv(1024)
            msg_rec = msg_rec.decode('ascii')
            msg = '\nConexión establecida con: ' + self.conexion.gethostname()
            c.send(msg.encode('utf8'))
            c.close()
            return msg_rec