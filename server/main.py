import cherrypy
import tcsapp

if __name__=="__main__":
    config = {
        'server.socket_host':'0.0.0.0',
        'server.socket_port':8080,
        'server.ssl_module':'builtin',
        'server.ssl_certificate':'server.crt',
        'server.ssl_private_key':'server.key'
    }
    cherrypy.config.update(config)
    cherrypy.quickstart(tcsapp.Root())