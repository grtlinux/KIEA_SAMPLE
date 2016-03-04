# nawk script (covered later in the chapter)
#   To demonstrate example 6.8 using this script, run
#   % nawk  -f ex_6.8.nawk  passwd

BEGIN {FS = ":"}
NF != 7 { printf("line %d, does not have 7 fields: %s\n",NR,$0)}
$1 !~ /[A-Za-z0-9]/{printf("line %d, nonalphanumeric user id: %s\n"NR,$0)}
$2 == "*" {printf("line %d, no password: %s\n",NR,$0)}
