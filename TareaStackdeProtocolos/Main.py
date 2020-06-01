import tkinter as tk
from tkinter import Button, Entry, Frame, Label, StringVar, messagebox
import tkinter.font as tkFont
import socket
from random import randint
from os import system


class Aplicacion(object):

    def __init__(self, lista):
        self.vista1 = None
        self.btnCliente = None
        self.btnServidor = None
        self.vista2 = None
        self.lblTitulo = None
        self.lblMensaje = None
        self.lblInfo = None
        self.lblIp = None
        self.txtMensaje = None
        self.txtIp = None
        self.btnEnviar = None
        self.uso = 0;
        self.mayus = False
        self.msj = None
        self.ip = None
        self.stringInf = ""
        self.lista = lista

    def initVista1(self):
        self.vista1 = tk.Tk()
        self.vista1.title("Tarea de Stack de Protocolos")
        self.vista1.geometry("400x200")
        self.vista1.resizable(0, 0)
        self.btnCliente = Button(self.vista1, text="Cliente", font=("Courier", 14, "bold"), command=lambda: self.initVista2(1))
        self.btnCliente.config(bg="#ffffff", fg="#1d1d1d", width=24, height=2);
        self.btnCliente.place(x=65, y=40)
        self.btnServidor = Button(self.vista1, text="Servidor", font=("Courier", 14, "bold"), command=lambda: self.initVista2(2))
        self.btnServidor.config(bg="#ffffff", fg="#1d1d1d", width=24, height=2);
        self.btnServidor.place(x=65, y=110)
        self.vista1.mainloop()

    def initVista2(self, uso):
        self.uso = uso;
        self.vista1.destroy();
        self.vista2 = tk.Tk()
        self.vista2.title("Tarea de Stack de Protocolos")
        self.vista2.geometry("1000x700")
        self.vista2.resizable(0, 0)
        self.msj = StringVar(self.vista2)
        self.ip = StringVar(self.vista2)
        if(uso == 1):
            self.mostrarVista2("Cliente")
        else:
            self.mostrarVista2("Servidor")
        self.vista2.mainloop();

    def mostrarVista2(self, texto):
        #Encabezado
        self.lblTitulo = Label(self.vista2, text=texto, borderwidth=2, relief="groove")
        self.lblTitulo.config(bg="#ffffff",font=("Courier", 16, tkFont.BOLD), width=75, height=2)
        self.lblTitulo.place(x=10, y=10)
        #Etiqueta del encabezado del mensaje
        self.lblMensaje = Label(self.vista2, text="Mensaje a Enviar", borderwidth=2, relief="groove");
        self.lblMensaje.config(bg="#ffffff",font=("Courier", 15, tkFont.BOLD), width=20, height=2)
        self.lblMensaje.place(x=10, y=80)
        #Etiqueta del contenido del mensaje
        self.lblInfo = Label(self.vista2, borderwidth=2, relief="groove")
        self.lblInfo.config(bg="#ffffff",font=("Courier", 16, tkFont.BOLD), height= 2, width = 54)
        self.lblInfo.place(x=280, y=80)
        #Etiqueta del encabezado de IP
        self.lblIp = Label(self.vista2, text="IP de destino", borderwidth=2, relief="groove");
        self.lblIp.config(bg="#ffffff",font=("Courier", 15, tkFont.BOLD), width=20, height=2)
        self.lblIp.place(x=10, y=150)
        #Text box de IP
        self.txtIp = Entry(self.vista2, textvariable=self.ip, borderwidth=3, relief="groove")
        self.txtIp.place(x=280, y=150, height = 50, width = 710)
        #Boton para enviar el mensaje
        self.btnEnviar = Button(self.vista2, text="Enviar mensaje", font=("Courier", 14, "bold"), command=self.obtenerDatos)
        self.btnEnviar.config(bg="#ffffff", fg="#1d1d1d", width=24, height=2)
        self.btnEnviar.place(x=350, y=630)

    def obtenerDatos(self):
        if(self.uso == 1):
            if(self.stringInf != "" and self.ip.get() != ""):
                if(messagebox.askyesno(message="Mensaje: "+self.stringInf+"\nIP Destino: "+self.ip.get(), title="Confirmar")):
                    print("ok")
            else:
                messagebox.showerror(title="Error", message="Debe llenar el campo de mensaje y el de ip de destino")
        else:
            messagebox.showerror(title="Error", message="De momento se encuentra en modo servidor, no puede enviar mensajes")

    def cambiarModo(self, nuevo):
        self.uso = nuevo
        self.stringInf = ""
        if(self.uso == 1):
            self.lblTitulo.configure(text = "Cliente")
        else:
            self.lblTitulo.configure(text = "Servidor")
        
    def actInfo(self, texto):
        self.stringInf += texto
        self.lblInfo.configure(text=self.stringInf)

class Presentacion(object):
    def __init__(self):
        self.mensaje = ""

    def setMensaje(self, mensaje):
        self.mensaje = mensaje

    def Codificar(self):
        codigo = ""
        for carac in self.mensaje:
            if ord(carac) <= 121:
                codigo +=chr(ord(carac) + 5)
            elif ord(carac)>= 122 and ord(carac) <= 126:
                codigo +=chr(ord(carac) + 39)
            elif ord(carac) >= 161:
                codigo +=chr(ord(carac) + 5)
        print(codigo)
        return codigo

    def Decoficar(self):
        mensaje = ""
        for carac in self.mensaje:
            if ord(carac) <= 126:
                mensaje +=chr(ord(carac) - 5)
            elif ord(carac) >= 161 and ord(carac) <= 165:
                mensaje +=chr(ord(carac) - 39)
            elif ord(carac) >= 161:
                mensaje +=chr(ord(carac) - 5)
        print(mensaje)
        return mensaje

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

class Transporte(object):
    def __init__(self):
        self.mensaje = ""
        self.segmentos = list()
        self.hayPerdida = False

    def getSegmentos(self):
        return self.segmentos

    def clearSegementos(self):
        self.segmentos.clear()

    def stringSegementos(self):
        return str(self.segmentos)

    def setMensaje(self, mensaje):
        self.mensaje = mensaje

    def Segmentar(self):
        aux = ""
        aux2 = 0
        perdida = randint(1, 2)
        for a in range(len(self.mensaje)):
            if(self.mensaje[a] != '%'):
                aux += self.mensaje[a]
            else:
                if(perdida == 1):
                    aux = "1"+aux
                else:
                    aux2 = randint(0, 1)
                    aux = str(aux2)+aux
                self.segmentos.append(aux)
                aux = ""
        if(perdida == 1):
            aux = "1"+aux
        else:
            aux2 = randint(0, 1)
            aux = str(aux2)+aux
        self.segmentos.append(aux)
        aux = ""
        for a in range (len(self.segmentos)):
            if(a == len(self.segmentos)-1):
                aux += self.segmentos[a]
            else:
                aux += self.segmentos[a]+"%"
        return aux;

    def Desegmentar(self):
        aux = self.mensaje
        aux2 = ""
        for a in range(len(self.mensaje)):
            if(self.mensaje[a] != '%'):
                aux2 += self.mensaje[a]
            else:
                aux3 = aux2[0]
                if(aux3 == "1"):
                    aux2 = aux2[1:len(aux)]
                    self.segmentos.append(aux2)
                aux2 = ""
        aux3 = aux2[0]
        if(aux3 == "1"):
            aux2 = aux2[1:len(aux)]
            self.segmentos.append(aux2)
        aux2 = ""
        for a in range (len(self.segmentos)):
            if(a == len(self.segmentos)-1):
                aux2 += self.segmentos[a]
            else:
                aux2 += self.segmentos[a]+"%"
        return aux2;

class Enlace(object):
    def __init__(self):
        self.tramas = []
    
    def stringTrama(self):
        return str(self.tramas)

    def clearTramas(self):
        self.tramas.clear()

    def getTramas(self):
        return self.tramas

    def convBinario(self,segmentos):
        trama = ""
        for segmento in segmentos:
            for carac in segmento:
                trama += format(ord(carac),'08b')
            self.tramas.append(trama)
            trama = ""
    
    def recibirTrama(self,trama):
        self.tramas.append(trama)

    def convDecimal(self):
        segmentos = []
        for trama in self.tramas:
            tam = len(trama)
            a = 0
            ind = 0
            palabra = ''
            binario = ''
            while tam >= 0:
                if a < 8 :
                    a += 1
                    tam -= 1
                    if ind < len(trama):
                        binario += trama[ind]
                        ind += 1
                else:
                    palabra += chr(int(binario, base=2))
                    binario = ""
                    a = 0
            segmentos.append(palabra)
        return segmentos

if __name__ == "__main__":
    system ("cls")
    lista = dict({
        "A":"Ʉ", "B":"ϐ", "C":"Ȼ", "D":"Δ", "E":"Ʃ", "F":"ƒ", "G":"Ƃ", "H":"Ħ",
        "I":"Ý", "J":"ł", "K":"ʞ", "L":"ȴ", "M":"μ", "N":"Ɲ", "O":"Φ", "P":"π",
        "Q":"ϑ", "R":"ɻ", "S":"Ϟ", "T":"ʈ", "U":"Ʊ", "V":"ƛ", "W":"Ψ", "X":"¤",
        "Y":"ɣ", "Z":"ζ", "0":"Ø", "1":"Ⅰ", "2":"Ⅱ", "3":"Ⅲ", "4":"Ⅳ", "5":"Ⅴ",
        "6":"Ⅵ", "7":"Ⅶ", "8":"Ⅷ", "9":"Ⅸ", "¡":"↑", "!":"↓", "?":"⌠", "¿":"⌡",
        "\"":"°", "#":"∞", "$":"†", "%":"└", "&":"٨", "'":"῭", "(":"ͼ", ")":"ͽ",
        "*":"•", "+":"Ꞩ", ",":"˼", "-":"Ꝛ", ".":"ᵨ", "/":"↙", ":":"჻", ";":"ἰ",
        "=":"≈", "@":"ⴋ", "[":"«", "\\":"↖", "]":"»", "_":"˽", "{":"﴾", "|":"٧","}":"﴿"
    })
    for key in lista:
        print(key+" : "+lista[key])
    #app = Aplicacion(lista)
    #app.initVista1()
    #
    #