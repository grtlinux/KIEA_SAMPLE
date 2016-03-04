/tom/ { count["tom"]++ }
/mary/ { count["mary"]++ }
END{print "There are " count["tom"] " Tom's in the file and \
" count["mary"]" Mary's in the file."}
