# members.py
def addmember(memberlist, newmembers): 
    if type(newmembers) not in (type([]), type(())): 
        newmembers = [newmembers] 
    for m in newmembers: 
        if m not in memberlist: # 기존 멤버가 아니면 
            memberlist.append(m)        # 추가! 

members = ['kim', 'lee', 'park', 'youn']
addmember(members, 'jung')
addmember(members, ['son', 'jo', 'jae'])
print members
