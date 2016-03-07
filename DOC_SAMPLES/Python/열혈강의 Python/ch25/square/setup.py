#!/usr/bin/env python

from distutils.core import setup, Extension

setup(name="square",
      version="1.0",
      description="simple class example - square",
      author="Gang Seong Lee",
      author_email="gslee@mail.gwu.ac.kr",
      url="http://www.python.or.kr/",
      ext_modules=[Extension("square", ["square.c"])]
     )
