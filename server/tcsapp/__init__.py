import security
import cherrypy
import time
import json

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
        
    @cherrypy.expose
    def jsontest(self):
        data = {'message':'Hello JSON world',
                'batman':'http://i.imgur.com/G11Bocg.gif'}
        return json.dumps(data)