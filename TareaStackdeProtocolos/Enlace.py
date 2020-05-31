class Enlace(object):
    def __init__(self, trama):
        self.trama = trama
    
    def convBinario(self):
        trama = ""
        for carac in self.trama:
            trama += format(ord(carac),'08b')
            print("Binario("+format(ord(carac),'08b')+")")
        print(trama)
        self.trama = trama;
    
    def convDecimal(self):
        tam = len(self.trama)
        a = 0
        ind = 0
        palabra = ''
        binario = ''
        while tam >= 0:
            if a < 8 :
                a += 1
                tam -= 1
                if ind < len(self.trama):
                    binario += self.trama[ind]
                    ind += 1
            else:
                print("Binario("+binario+")")
                palabra += chr(int(binario, base=2))
                binario = ""
                a = 0
        self.trama = palabra
        print(palabra)

cosa = Enlace("(Hola90)")
cosa.convBinario()
cosa.convDecimal()

