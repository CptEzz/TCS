"""Handles security for the application"""

import hashlib

def passwd(data,salt):
    """Takes a string data and string salt and hashes it suitably for storing passwords"""
    return stdhash(salt+data)