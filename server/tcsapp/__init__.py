import security
import cherrypy

class Root(object):
    @cherrypy.expose
    def index(self):
        returnData = "Hello World!\n"
        returnData += security.passwd(returnData,"salt")
        return returnData