import sqlite3
import os
#import tcsapp.test
from .. import security




database = 'data.sqlite'
con = sqlite3.connect(database)
cur = con.cursor()



def init():
    cur.execute('create table student(ID, name, email, password, salt)')
    cur.execute('create table class(ID, code, name, teacher)')
    cur.execute('create table enrollment(studentID, classID, period, day, room)')
    print 'Initialised'

def register(name, email, password):
    salt = os.urandom(30)
    cur.execute('insert into student values (?, ?, ?, ?, ?)', (ID, name, email, passwd(password, salt), salt))
    



def createClass(code, name, teacher):
    cur.execute('insert into class values (?, ?, ?, ?, ?)', (ID, code, name, teacher))



def enrol(studentID, classID, period, day, room):
    cur.execute('insert into enrollment values (?, ?, ?, ?, ?)', (studentID, classID, period, day, room))


