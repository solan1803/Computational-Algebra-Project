function bifHenon(b,N)

hold on

xlabel('$a$','Interpreter','latex');
ylabel('$x$','Interpreter','latex');
a=0:0.001:2;
x=-0.1;
y=0.1;
x0=x;
y0=y;
for i=1:N;
    x=1-a.*(x0.^2)+y0;
    y=b.*x0;
    x0=x;
    y0=y;
end
for i=1:100;
    x=1-a.*(x0.^2)+y0;
    y=b.*x0;
    x0=x;
    y0=y;
    plot(a,x,'.','MarkerSize',2);
end