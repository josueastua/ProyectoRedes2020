
from random import randint

class Transporte(object):
    def __init__(self, mensaje):
        self.mensaje = mensaje
        self.segmentos = list()
        self.hayPerdida = False

    def getSegmentos(self):
        return self.segmentos

    def clearSegementos(self):
        self.segmentos.clear()

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
        

T = Transporte("la%vida%es%bella")
seg = T.Segmentar()
print(seg)
T.clearSegementos()
T.setMensaje(seg)
print(T.Desegmentar())
        