import hashlib

def passwd(data,salt):
    """Takes a string data and string salt and hashes it suitably for storing passwords"""
    return hashlib.sha512(salt+data).hexdigest()