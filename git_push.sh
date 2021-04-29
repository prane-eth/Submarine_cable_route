#!/bin/bash

# This file is to push new code to Github page

# read message if entered as CLI variable
message="$1"  

if [ -z "$message" ]
then
	message='Edited files'
fi

git add .
git commit -m "$message"
git push