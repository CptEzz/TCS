import security
import cherrypy
import time
import json
import database

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
    
    @cherrypy.expose
    def echotest(self,stuff="NULL"):
        return stuff
        
    @cherrypy.expose
    def jsonechotest(self,stuff=None):
        data = dict()
        if not(stuff):
            data['errorc'] = 400
            data['errorm'] = "Missing parameter (jsondata)"
            return json.dumps(data)
        else:
            data['echo'] = stuff
            try:
                decoded = json.loads(stuff)
            except ValueError as e:
                data['errorc'] = 400
                data['errorm'] = e.message
                return json.dumps(data)
            for key in decoded:
                data[key] = decoded[key]
        return json.dumps(data)
    
    @cherrypy.expose
    def dummyauth(self,user=None,password=None):
        data = dict()
        if not(user) or not(password):
            data['errorc'] = 400
            data['errorm'] = "Missing parameter (user/password)"
            return json.dumps(data)
            
        authcode = database.dummyAuthenticate(user,password)
        if not(authcode):
            data['errorc'] = 401
            data['errorm'] = "Invalid user/password combination"
        data['authcode'] = authcode
        return json.dumps(data)
            