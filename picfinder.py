#!/usr/bin/python3

import os
import shutil

extensions = ["jpg", "jpeg", "png", "gif", "bmp", "tiff"];

# Check if a file is a picture
def isPic (file):
    for extension in extensions:
        if (file.endswith("."+extension) or file.endswith("."+extension.upper())):
            return True
    return False

# Traverse directory (and subdirectories)
def findPics(root):
    dir = os.listdir(root)
    res = []
    for f in dir:
        path = os.path.join(root,f)
        if (os.path.isdir(path)):
            res = res + findPics(path)
        elif (os.path.isfile(path) and isPic(f)):
            res.append(path)
    return res

# Save pictures to directory
def savePics (pics, dest):
    if not os.path.exists(dest):
        os.makedirs(dest)
        for pic in pics:
            print (os.path.basename(pic))
            try:
                shutil.copy(pic, os.path.join(dest, os.path.basename(pic)))
            except IOError as e:
                print("Unable to copy file: %s" % e)
            except:
                print("Unexpected error:", sys.exc_info())
    else:
        print ("ERROR: Directory Exists")



print ("PICFINDER!")

# List extensions to look for
print ("Will search for files ending: ", end="")
for e in extensions:
    if (not e == extensions[-1]):
        print (e, end=", ")
    else:
        print (e)
print()

# Get and Check source directory
source = input("Where do you want me to look for pictures? ");
if (not os.path.isdir(source)):
    print("That directory doesn't exists, will quit now")
    quit()
else:
    print ("Will look in: "+source)
    print()

# Get and Check destination directory
destination = input("Where do you want me to save them? ");
if (os.path.exists(destination)):
    print("That directory already exists, will quit now")
    quit()
else:
    print ("Will save to: "+destination)
    print()

## TEST
# source = "PICS"
# destination = "found_pics"

# findPics
found = findPics(source)
print ("I found the following pictures: ")
for pic in found:
    print ("  • "+pic)
print()

# savePics
ui = input("Shall I continue to copy them? ").lower()
if (ui == "y" or ui == "yes" or ui == "yeah" or ui == "yep" or ui == "ok" or ui == "okay"):
    savePics(found, destination)
    print("\nAll Done! Bye :)")
else:
    print("\nOkay, I won't. See you soon!")
