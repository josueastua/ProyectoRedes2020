import tkinter as tk
from tkinter import Button, Entry, Frame, Label, StringVar, messagebox
import tkinter.font as tkFont
import socket
from random import randint
from os import system

class Interfaz(object):
    def __init__(self, lista, app):
        self.vista1 = None
        self.btnCliente = None
        self.btnServidor = None
        self.vista2 = None
        self.lblTitulo = None
        self.lblMensaje = None
        self.lblIp = None
        self.txtMensaje = None
        self.txtIp = None
        self.btnEnviar = None
        self.uso = 0;
        self.mayus = False
        self.msj = None
        self.straux = None
        self.ip = None
        self.lista = lista
        self.app = app

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
        self.vista2.geometry("1000x750")
        self.vista2.resizable(0, 0)
        self.msj = StringVar(self.vista2)
        self.ip = StringVar(self.vista2)
        self.straux = StringVar(self.vista2)
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
        self.txtMensaje = Entry(self.vista2, textvariable=self.msj, borderwidth=3, relief="groove")
        self.txtMensaje.place(x=280, y=80, height = 50, width = 710)
        self.txtMensaje.config(state='disabled')
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
        self.btnEnviar.place(x=350, y=685)
        #botone teclas
        btnA = Button(self.vista2, text="A : Ʉ", command=lambda: self.escribirMensaje("A"), height = 2, width = 10)
        btnA.place(x=170, y=230)
        btnB = Button(self.vista2, text="B : ϐ", command=lambda: self.escribirMensaje("B"), height = 2, width = 10)
        btnB.place(x=250, y=230)
        btnC = Button(self.vista2, text="C : Ȼ", command=lambda: self.escribirMensaje("C"), height = 2, width = 10)
        btnC.place(x=330, y=230)
        btnD = Button(self.vista2, text="D : Δ", command=lambda: self.escribirMensaje("D"), height = 2, width = 10)
        btnD.place(x=410, y=230)
        btnE = Button(self.vista2, text="E : Ʃ", command=lambda: self.escribirMensaje("E"), height = 2, width = 10)
        btnE.place(x=490, y=230)
        btnF = Button(self.vista2, text="F : ƒ", command=lambda: self.escribirMensaje("F"), height = 2, width = 10)
        btnF.place(x=570, y=230)
        btnG = Button(self.vista2, text="G : Ƃ", command=lambda: self.escribirMensaje("G"), height = 2, width = 10)
        btnG.place(x=650, y=230)
        btnH = Button(self.vista2, text="H : Ħ", command=lambda: self.escribirMensaje("H"), height = 2, width = 10)
        btnH.place(x=730, y=230)

        btnI = Button(self.vista2, text="I : Ý", command=lambda: self.escribirMensaje("I"), height = 2, width = 10)
        btnI.place(x=170, y=280)
        btnJ = Button(self.vista2, text="J : ł", command=lambda: self.escribirMensaje("J"), height = 2, width = 10)
        btnJ.place(x=250, y=280)
        btnK = Button(self.vista2, text="K : ʞ", command=lambda: self.escribirMensaje("K"), height = 2, width = 10)
        btnK.place(x=330, y=280)
        btnL = Button(self.vista2, text="L : ȴ", command=lambda: self.escribirMensaje("L"), height = 2, width = 10)
        btnL.place(x=410, y=280)
        btnM = Button(self.vista2, text="M : μ", command=lambda: self.escribirMensaje("M"), height = 2, width = 10)
        btnM.place(x=490, y=280)
        btnN = Button(self.vista2, text="N : Ɲ", command=lambda: self.escribirMensaje("N"), height = 2, width = 10)
        btnN.place(x=570, y=280)
        btnO = Button(self.vista2, text="O : Φ", command=lambda: self.escribirMensaje("O"), height = 2, width = 10)
        btnO.place(x=650, y=280)
        btnP = Button(self.vista2, text="P : π", command=lambda: self.escribirMensaje("P"), height = 2, width = 10)
        btnP.place(x=730, y=280)

        btnQ = Button(self.vista2, text="Q : ϑ", command=lambda: self.escribirMensaje("Q"), height = 2, width = 10)
        btnQ.place(x=170, y=330)
        btnR = Button(self.vista2, text="R : ɻ", command=lambda: self.escribirMensaje("R"), height = 2, width = 10)
        btnR.place(x=250, y=330)
        btnS = Button(self.vista2, text="S : Ϟ", command=lambda: self.escribirMensaje("S"), height = 2, width = 10)
        btnS.place(x=330, y=330)
        btnT = Button(self.vista2, text="T : ʈ", command=lambda: self.escribirMensaje("T"), height = 2, width = 10)
        btnT.place(x=410, y=330)
        btnU = Button(self.vista2, text="U : Ʊ", command=lambda: self.escribirMensaje("U"), height = 2, width = 10)
        btnU.place(x=490, y=330)
        btnV = Button(self.vista2, text="V : ƛ", command=lambda: self.escribirMensaje("V"), height = 2, width = 10)
        btnV.place(x=570, y=330)
        btnW = Button(self.vista2, text="W : Ψ", command=lambda: self.escribirMensaje("W"), height = 2, width = 10)
        btnW.place(x=650, y=330)
        btnX = Button(self.vista2, text="X : ¤", command=lambda: self.escribirMensaje("X"), height = 2, width = 10)
        btnX.place(x=730, y=330)

        btnY = Button(self.vista2, text="Y : ɣ", command=lambda: self.escribirMensaje("Y"), height = 2, width = 10)
        btnY.place(x=170, y=380)
        btnZ = Button(self.vista2, text="Z : ζ", command=lambda: self.escribirMensaje("Z"), height = 2, width = 10)
        btnZ.place(x=250, y=380)
        btn0 = Button(self.vista2, text="0 : Ø", command=lambda: self.escribirMensaje("0"), height = 2, width = 10)
        btn0.place(x=330, y=380)
        btn1 = Button(self.vista2, text="1 : Ⅰ", command=lambda: self.escribirMensaje("1"), height = 2, width = 10)
        btn1.place(x=410, y=380)
        btn2 = Button(self.vista2, text="2 : Ⅱ", command=lambda: self.escribirMensaje("2"), height = 2, width = 10)
        btn2.place(x=490, y=380)
        btn3 = Button(self.vista2, text="3 : Ⅲ", command=lambda: self.escribirMensaje("3"), height = 2, width = 10)
        btn3.place(x=570, y=380)
        btn4 = Button(self.vista2, text="4 : Ⅳ", command=lambda: self.escribirMensaje("4"), height = 2, width = 10)
        btn4.place(x=650, y=380)
        btn5 = Button(self.vista2, text="5 : Ⅴ", command=lambda: self.escribirMensaje("5"), height = 2, width = 10)
        btn5.place(x=730, y=380)

        btn6 = Button(self.vista2, text="6 : Ⅵ", command=lambda: self.escribirMensaje("6"), height = 2, width = 10)
        btn6.place(x=170, y=430)
        btn7 = Button(self.vista2, text="7 : Ⅶ", command=lambda: self.escribirMensaje("7"), height = 2, width = 10)
        btn7.place(x=250, y=430)
        btn8 = Button(self.vista2, text="8 : Ⅷ", command=lambda: self.escribirMensaje("8"), height = 2, width = 10)
        btn8.place(x=330, y=430)
        btn9 = Button(self.vista2, text="9 : Ⅸ", command=lambda: self.escribirMensaje("9"), height = 2, width = 10)
        btn9.place(x=410, y=430)
        btn161 = Button(self.vista2, text="¡ : ↑", command=lambda: self.escribirMensaje("¡"), height = 2, width = 10)
        btn161.place(x=490, y=430)
        btn33 = Button(self.vista2, text="! : ↓", command=lambda: self.escribirMensaje("!"), height = 2, width = 10)
        btn33.place(x=570, y=430)
        btn63 = Button(self.vista2, text="? : ⌠", command=lambda: self.escribirMensaje("?"), height = 2, width = 10)
        btn63.place(x=650, y=430)
        btn191 = Button(self.vista2, text="¿ : ⌡", command=lambda: self.escribirMensaje("¿"), height = 2, width = 10)
        btn191.place(x=730, y=430)

        btnComa = Button(self.vista2, text="\" : °", command=lambda: self.escribirMensaje("\""), height = 2, width = 10)
        btnComa.place(x=170, y=480)
        btn35 = Button(self.vista2, text="# : ∞", command=lambda: self.escribirMensaje("#"), height = 2, width = 10)
        btn35.place(x=250, y=480)
        btn36 = Button(self.vista2, text="$ : †", command=lambda: self.escribirMensaje("$"), height = 2, width = 10)
        btn36.place(x=330, y=480)
        btn37 = Button(self.vista2, text="% : └", command=lambda: self.escribirMensaje("%"), height = 2, width = 10)
        btn37.place(x=410, y=480)
        btn38 = Button(self.vista2, text="& : ٨", command=lambda: self.escribirMensaje("&"), height = 2, width = 10)
        btn38.place(x=490, y=480)
        btn39 = Button(self.vista2, text="' : ῭", command=lambda: self.escribirMensaje("'"), height = 2, width = 10)
        btn39.place(x=570, y=480)
        btn40 = Button(self.vista2, text="( : ͼ", command=lambda: self.escribirMensaje("("), height = 2, width = 10)
        btn40.place(x=650, y=480)
        btn41 = Button(self.vista2, text=") : ͽ", command=lambda: self.escribirMensaje(")"), height = 2, width = 10)
        btn41.place(x=730, y=480)

        btn42 = Button(self.vista2, text="* : •", command=lambda: self.escribirMensaje("*"), height = 2, width = 10)
        btn42.place(x=170, y=530)
        btn43 = Button(self.vista2, text="+ : Ꞩ", command=lambda: self.escribirMensaje("+"), height = 2, width = 10)
        btn43.place(x=250, y=530)
        btn44 = Button(self.vista2, text=", : ˼", command=lambda: self.escribirMensaje(","), height = 2, width = 10)
        btn44.place(x=330, y=530)
        btn45 = Button(self.vista2, text="- : Ꝛ", command=lambda: self.escribirMensaje("-"), height = 2, width = 10)
        btn45.place(x=410, y=530)
        btn46 = Button(self.vista2, text=". : ᵨ", command=lambda: self.escribirMensaje("."), height = 2, width = 10)
        btn46.place(x=490, y=530)
        btn47 = Button(self.vista2, text="/ : ↙", command=lambda: self.escribirMensaje("/"), height = 2, width = 10)
        btn47.place(x=570, y=530)
        btn58 = Button(self.vista2, text=": : ჻", command=lambda: self.escribirMensaje(":"), height = 2, width = 10)
        btn58.place(x=650, y=530)
        btn59 = Button(self.vista2, text="; : ἰ", command=lambda: self.escribirMensaje(";"), height = 2, width = 10)
        btn59.place(x=730, y=530)

        btn61 = Button(self.vista2, text="= : ≈", command=lambda: self.escribirMensaje("="), height = 2, width = 10)
        btn61.place(x=170,y=580)
        btn64 = Button(self.vista2, text="@ : ⴋ", command=lambda: self.escribirMensaje("@"), height = 2, width = 10)
        btn64.place(x=250,y=580)
        btn91 = Button(self.vista2, text="[ : «", command=lambda: self.escribirMensaje("["), height = 2, width = 10)
        btn91.place(x=330,y=580)
        btnBarra = Button(self.vista2, text="\ : ↖", command=lambda: self.escribirMensaje("\\"), height = 2, width = 10)
        btnBarra.place(x=410,y=580)
        btn93 = Button(self.vista2, text="] : »", command=lambda: self.escribirMensaje("]"), height = 2, width = 10)
        btn93.place(x=490,y=580)
        btn95 = Button(self.vista2, text="_ : ˽", command=lambda: self.escribirMensaje("_"), height = 2, width = 10)
        btn95.place(x=570,y=580)
        btn123 = Button(self.vista2, text="{ : <", command=lambda: self.escribirMensaje("{"), height = 2, width = 10)
        btn123.place(x=650,y=580)
        btn124 = Button(self.vista2, text="| : ٧", command=lambda: self.escribirMensaje("|"), height = 2, width = 10)
        btn124.place(x=730,y=580)

        btn125 = Button(self.vista2, text="} : >", command=lambda: self.escribirMensaje("}"), height = 2, width = 10)
        btn125.place(x=330, y=630)
        btnEsp = Button(self.vista2, text=" ", command=lambda: self.escribirMensaje(" "), height = 2, width = 21)
        btnEsp.place(x=410, y=630)
        btnDel = Button(self.vista2, text="<-", command=lambda: self.escribirMensaje("<-"), height = 2, width = 10)
        btnDel.place(x=570, y=630)

    def escribirMensaje(self, letra):
        print(lista["}"])
        if(letra != "<-"):
            self.straux.set(self.straux.get() + letra)
            txt = self.msj.get()
            txt += lista[letra]
            self.msj.set(txt)
        else:
            txt = self.msj.get()
            txt = txt[0 : len(txt)-1]
            self.msj.set(txt)
            txt = self.straux.get()
            self.straux.set(txt[0 : len(txt)-1])

    def obtenerDatos(self):
        if(self.uso == 1):
            if(self.msj.get() != "" and self.ip.get() != ""):
                if(messagebox.askyesno(message="Mensaje: "+self.msj.get()+"\nMensaje 2: "+self.straux.get()+"\nIP Destino: "+self.ip.get(), title="Confirmar")):
                    app.iniciarComunicacion(self.straux.get(), self.ip.get())
            else:
                messagebox.showerror(title="Error", message="Debe llenar el campo de mensaje y el de ip de destino")
        else:
            messagebox.showerror(title="Error", message="De momento se encuentra en modo servidor, no puede enviar mensajes")

    def cambiarModo(self, nuevo):
        self.uso = nuevo
        if(self.uso == 1):
            self.lblTitulo.configure(text = "Cliente")
        else:
            self.lblTitulo.configure(text = "Servidor")
      

class Aplicacion(object):
    def __init__(self, presentacion, sesion, transporte, enlace):
        self.presentacion = presentacion
        self.sesion = sesion
        self.transporte = transporte
        self.enlace = enlace
        self.sesion.setEnlace(enlace)
        self.gui = None

    def setGui(self, gui):
        self.gui = gui

    def iniciarComunicacion(self, mensaje, ip):
        self.sesion.setIpdestino(ip)
        self.presentacion.setMensaje(mensaje)
        mensaje = self.presentacion.Codificar()
        self.transporte.setMensaje(mensaje)      
        self.transporte.Segmentar()
        seg = self.transporte.getSegmentos()
        self.enlace.convBinario(seg)
        print(self.enlace.getTramas())
        '''
        trama = ""
        for trama in self.enlace.getTramas():
            self.sesion.modoCliente(trama)
        self.enlace.clearTramas()
        self.transporte.clearSegementos()'''
    

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
        self.ipdestino = ""
        self.enlace = None

    def setEnlace(self, enlace):
        self.enlace = enlace

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
        for a in range(len(self.mensaje)):
            aux += self.mensaje[a]
            if(a != 0 and a % 15 == 0):
                self.segmentos.append(aux)
                aux = ""
        if(aux != ""):
            self.segmentos.append(aux)
        print(self.segmentos)
        """
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
        """

    def Desegmentar(self, lista):
        aux = ""
        aux2 = ""
        for aux2 in lista:
            aux += aux2
        self.mensaje = aux
        '''
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
        return aux2;'''

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
        self.tramas.append("00000000")
    
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
        "=":"≈", "@":"ⴋ", "[":"«", "\\":"↖", "]":"»", "_":"˽", "{":"<", "|":"٧",
        "}":">", " ":" ", "<-":"<-"
    })
    Sesion = Sesion()
    Presentacion = Presentacion()
    Transporte = Transporte()
    Enlace = Enlace()
    app = Aplicacion(Presentacion, Sesion, Transporte, Enlace)
    GUI = Interfaz(lista, app)
    app.setGui(GUI)
    GUI.initVista1()
    
    