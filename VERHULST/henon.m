function henon(a,b,N)

x=zeros(1,N);
y=zeros(1,N);
x(1)=0.2;
y(1)=0.2;
for i=1:N;
x(i+1)=1-a.*(x(i)).^(2)+y(i);
y(i+1)=b.*x(i);
end
plot(x,y,'.','MarkerSize',2);
xlabel('$x$','Interpreter','latex');
ylabel('$y$','Interpreter','latex');