import cherrypy
import tcsapp

if __name__=="__main__":
    config = {
        'server.socket_host':'0.0.0.0',
        'server.socket_port':8080
    }
    cherrypy.config.update(config)
    cherrypy.quickstart(tcsapp.Root())