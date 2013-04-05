import sqlite3
import os
from .. import security

databaseDirectory = 'data.sqlite'
# By default this creates the database in the current directory,
# in this case ...\GitHub\TCS\server\tcsapp\database

def getConnection():
    # Ensures every method call is run on a unique connection, which allows
    # multithreading.
    return sqlite3.connect(databaseDirectory)

def init():
    # Initialises the system, only to be run once.
    connection = getConnection()
    cur = connection.cursor()
    cur.execute('create table student(ID, name, email, password, salt)')
    cur.execute('create table class(ID, code, name, teacher)')
    cur.execute('create table enrollment(studentID, classID, period, day, room)')
    cur.commit()
    connection.close()
    print 'Initialised'

def register(name, email, password):
    # Creation of a new user
    connection = getConnection()
    cur = connection.cursor()
    salt = os.urandom(30)
    cur.execute('insert into student values (?, ?, ?, ?, ?)', (ID, name, email, passwd(password, salt), salt))
    cur.commit()
    connection.close()
    
def createClass(code, name, teacher):
    # Creation of a new class
    connection = getConnection()
    cur = connection.cursor()
    cur.execute('insert into class values (?, ?, ?, ?, ?)', (ID, code, name, teacher))
    cur.commit()
    connection.close()

def enrol(studentID, classID, period, day, room):
    # Creation of a new enrollment
    connection = getConnection()
    cur = connection.cursor()
    cur.execute('insert into enrollment values (?, ?, ?, ?, ?)', (studentID, classID, period, day, room))
    cur.commit()
    connection.close()

"""Still yet to write methods for editing and/or ammending of Student, Class
or Enrollments"""
