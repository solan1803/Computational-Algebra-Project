function bifVerhulst(N)
 
hold on
r=1:0.001:3;
x=0.1;
xlabel('$r$','Interpreter','latex');
ylabel('$x$','Interpreter','latex');
for i=1:N
    x = x + r.*x.*(1-x);
end
for i=1:200
    x = x + r.*x.*(1-x);
    plot(r,x,'.','MarkerSize',2);
end

