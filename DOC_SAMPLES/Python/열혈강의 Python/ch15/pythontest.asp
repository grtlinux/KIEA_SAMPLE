<%@ Language = Python %>
<%
import sys
sys.stdout.write = Response.Write

s = "서버측 코드 실행 성공"
print s
l = s.split()
l.reverse()
print '<br>단어 역으로 바꾸기<br>'
print ' '.join(l)
print 'Testing..'
%>
<br />

<script language="JavaScript">
document.write("클라이언트측 코드 실행 성공")
</script>
<br />

<script language="Python" runat="server">
print '다시 서버측 코드..'
</script>
