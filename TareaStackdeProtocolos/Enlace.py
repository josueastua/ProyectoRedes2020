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




