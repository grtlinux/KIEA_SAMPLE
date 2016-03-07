#!/usr/bin/env python

from distutils.core import setup, Extension

setup(name="sample",
      version="1.0",
      description="sample extension module",
      author="Gang Seong Lee",
      author_email="gslee@mail.gwu.ac.kr",
      url="http://www.python.or.kr/",
      ext_modules=[Extension("sample", ["sample.c"])]
     )
