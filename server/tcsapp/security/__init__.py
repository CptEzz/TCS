"""Handles security for the application"""

import hashlib

def stdhash(data):
    return hashlib.sha512(data).hexdigest()

def passwd(data,salt):
    """Takes a string data and string salt and hashes it suitably for storing passwords"""
    return stdhash(salt+data)