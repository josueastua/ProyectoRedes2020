import socket
from os import system
from time import sleep

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
        msg = '\nConexion establecida con: ' + socket.gethostname()
        c.send(msg.encode('utf8'))
        msg_rec = c.recv(1024)
        msg_rec = msg_rec.decode('ascii')
        print("\nMensaje decodificado: "+msg_rec+"\n")
        c.close()
        

if __name__ == "__main__":
    host = ""
    puerto = 44440
    iniciarServidor(host,puerto)
