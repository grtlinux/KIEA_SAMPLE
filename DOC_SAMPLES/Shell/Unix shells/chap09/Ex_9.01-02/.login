# (The .login File)
stty -istrip
stty erase ^H
#
#If possible start the windows system.
#Give a user a chance to bail out
#
if ( `tty` == "/dev/console" ) then
   if ( $TERM == "sun" || $TERM == "AT386" ) then
      if ( ${?OPENWINHOME} == 0 ) then
	 setenv OPENWINHOME /usr/openwin
      endif
      echo ""
      echo -n "Starting OpenWindows in 5 seconds\
      (type Control-C to interrupt)"
      sleep 5
      $OPENWINHOME/bin/openwin
      clear
      logout
   rndif
endif
