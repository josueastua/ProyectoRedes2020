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

