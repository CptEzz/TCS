import sqlite3
import os
import security

con = sqlite3.connect('data.sqlite')
cur = con.cursor()



def init():
    cur.execute('create table student(ID, name, email, password, salt)')
    cur.execute('create table class(ID, code, name, teacher)')
    cur.execute('create table enrollment(studentID, classID, period, day, room)')

def register(name, email, password):
    salt = os.urandom(30)
    cur.execute("insert into student values (?, ?, ?, ?, ?)", (ID, name, email, passwrd(password, salt), salt))
    



def createClass(code, name, teacher):
    cur.execute("insert into class values (?, ?, ?, ?, ?)", (ID, code, name, teacher))



def enrol(studentID, classID, period, day, room):
    return

