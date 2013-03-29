import cherrypy
import tcsapp

if __name__=="__main__":
    cherrypy.quickstart(tcsapp.Root())