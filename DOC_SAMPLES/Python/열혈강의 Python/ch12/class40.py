# class40.py
class xmldic(dict): 
	def __repr__(self): 
		res = ['\n<dictionary>'] 
		for k,v in self.items(): 
			res.append('<member>') 
			res.append('<name>%s</name>' % k) 
			res.append('<value>%s</value>' % repr(v)) 
			res.append('</member>') 
		res.append('\n</dictionary>') 
		return '\n'.join(res)

d1 = xmldic({'one':1, 'two':2})
d3 = xmldic({'numbers':d1})
print d3
