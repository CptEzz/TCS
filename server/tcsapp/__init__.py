import security
import cherrypy
import time

class Root(object):
    @cherrypy.expose
    def index(self):
        returnData = "Hello World!\n"        
        returnData += security.passwd(returnData,"salt")
        if 'last' in cherrypy.request.cookie:
            last = cherrypy.request.cookie
            returnData +="\nYou were last here at %s" % (last)
        cherrypy.response.cookie['last'] = str(time.time())
        return returnData