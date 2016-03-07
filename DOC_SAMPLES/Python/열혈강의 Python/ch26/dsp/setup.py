#!/usr/bin/env python

from distutils.core import setup, Extension

setup(name="dsp",
      version="1.0",
      description="DSP extension module",
      author="Gang Seong Lee",
      author_email="gslee@mail.gwu.ac.kr",
      url="http://www.python.or.kr/",
      ext_modules=[Extension("dsp", ["dspmodule.c"])]
     )
