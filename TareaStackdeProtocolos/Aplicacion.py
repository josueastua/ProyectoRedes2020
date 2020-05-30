import tkinter as tk
from tkinter import Button, Entry, Label, StringVar, messagebox
import tkinter.font as tkFont

class Aplicacion(object):

    def __init__(self):
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
        self.msj = None
        self.ip = None

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
        self.vista2.geometry("800x600")
        self.vista2.resizable(0, 0)
        self.msj = StringVar(self.vista2)
        self.ip = StringVar(self.vista2)
        if(uso == 1):
            self.mostrarVista2("Cliente")
        else:
            self.mostrarVista2("Servidor")
        self.vista2.mainloop();

    def mostrarVista2(self, texto):
        self.lblTitulo = Label(self.vista2, text=texto, borderwidth=2, relief="groove")
        self.lblTitulo.config(bg="#ffffff",font=("Courier", 16, tkFont.BOLD), width=59, height=2)
        self.lblTitulo.place(x=10, y=10)
        self.txtMensaje = Entry(self.vista2, textvariable=self.msj, borderwidth=3, relief="groove")
        self.txtMensaje.place(x=280, y=100, height = 50, width = 500)
        self.lblMensaje = Label(self.vista2, text="Ingrese el mensaje", borderwidth=2, relief="groove");
        self.lblMensaje.config(bg="#ffffff",font=("Courier", 15, tkFont.BOLD), width=20, height=2)
        self.lblMensaje.place(x=10, y=100)
        self.lblIp = Label(self.vista2, text="IP de destino", borderwidth=2, relief="groove");
        self.lblIp.config(bg="#ffffff",font=("Courier", 15, tkFont.BOLD), width=20, height=2)
        self.lblIp.place(x=10, y=170)
        self.txtIp = Entry(self.vista2, textvariable=self.ip, borderwidth=3, relief="groove")
        self.txtIp.place(x=280, y=170, height = 50, width = 500)
        self.lblInfo = Label(self.vista2, borderwidth=2, relief="groove")
        self.lblInfo.config(bg="#ffffff",font=("Courier", 16, tkFont.BOLD), height= 12, width = 59)
        self.lblInfo.place(x=10, y=240)
        self.btnEnviar = Button(self.vista2, text="Enviar mensaje", font=("Courier", 14, "bold"), command=self.obtenerDatos)
        self.btnEnviar.config(bg="#ffffff", fg="#1d1d1d", width=24, height=2)
        self.btnEnviar.place(x=230, y=530)

    def obtenerDatos(self):
        if(self.uso == 1):
            if(self.msj.get() != "" and self.ip.get() != ""):
                if(messagebox.askyesno(message="Mensaje: "+self.msj.get()+"\nIP Destino: "+self.ip.get(), title="Confirmar")):
                    print("ok")
            else:
                messagebox.showerror(title="Error", message="Debe llenar el campo de mensaje y el de ip de destino")
        else:
            messagebox.showerror(title="Error", message="De momento se encuentra en modo servidor, no puede enviar mensajes")



def diHola():
    print("Hola")

"""
ventana = tk.Tk()
helv36 = tkFont.Font(family='Courier', size=14, weight=tkFont.BOLD)
btnEnviar = None
txtMensaje = None
txtIp = None
lblTitulo = None
lblInf = None


def primeraVentana():
    global ventana
    ventana.title("Tarea de Stack de Protocolos")
    ventana.geometry("400x200")
    ventana.resizable(0, 0)
    ventana.configure(bg="#1d1d1d")
    btnCliente = Button(ventana, text="Cliente", font=helv36, command=segundaVentana1)
    btnCliente.config(bg="#ffffff", fg="#1d1d1d", width=24, height=2);
    btnCliente.place(x=65, y=40)
    btnServidor = Button(ventana, text="Servidor", font=helv36, command=segundaVentana2)
    btnServidor.config(bg="#ffffff", fg="#1d1d1d", width=24, height=2);
    btnServidor.place(x=65, y=110)
    ventana.mainloop()


def segundaVentana1():
    global ventana, btnEnviar, txtMensaje, txtIp, lblTitulo, lblInf
    ventana.destroy();
    ventana2 = tk.Tk()
    ventana2.title("Tarea de Stack de Protocolos")
    ventana2.geometry("800x600")
    ventana2.resizable(0, 0)
    ventana2.configure(bg="#1d1d1d")

    lblTitulo = Label(ventana2, text="Cliente")
    lblTitulo.config(bg="#ffffff",font=("Courier", 16, tkFont.BOLD), width=59, height=2)
    lblTitulo.place(x=10, y=10)
    txtMensaje = Entry(ventana2)
    txtMensaje.place(x=280, y=100, height = 50, width = 500)
    lblMensaje = Label(ventana2, text="Ingrese el mensaje");
    lblMensaje.config(bg="#ffffff",font=("Courier", 15, tkFont.BOLD), width=20, height=2)
    lblMensaje.place(x=10, y=100)
    lblInf = Label(ventana2, Tex)
    ventana2.mainloop()

def segundaVentana2():
    global ventana, btnEnviar, txtMensaje, txtIp, lblTitulo, lblInf
    ventana.destroy();
    ventana2 = tk.Tk()
    ventana2.title("Tarea de Stack de Protocolos")
    ventana2.geometry("800x600")
    ventana2.resizable(0, 0)
    ventana2.configure(bg="#1d1d1d")
    ventana2.mainloop()
"""
if __name__ == "__main__":
    app = Aplicacion()
    app.initVista1()