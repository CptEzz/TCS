import sqlite3
import os
from .. import security

database = 'data.sqlite'
sqlite3.connect(database)

def getConnection():
    return sqlite3.connect(database)

def init():
    cur = getConnection().cursor()
    cur.execute('create table student(ID, name, email, password, salt)')
    cur.execute('create table class(ID, code, name, teacher)')
    cur.execute('create table enrollment(studentID, classID, period, day, room)')
    cur.commit()
    cur.close()
    print 'Initialised'

def register(name, email, password):
    cur = getConnection().cursor()
    salt = os.urandom(30)
    cur.execute('insert into student values (?, ?, ?, ?, ?)', (ID, name, email, passwd(password, salt), salt))
    cur.commit()
    cur.close()
    
def createClass(code, name, teacher):
    cur = getConnection().cursor()
    cur.execute('insert into class values (?, ?, ?, ?, ?)', (ID, code, name, teacher))
    cur.commit()
    cur.close()

def enrol(studentID, classID, period, day, room):
    cur = getConnection().cursor()
    cur.execute('insert into enrollment values (?, ?, ?, ?, ?)', (studentID, classID, period, day, room))
    cur.commit()
    cur.close()
