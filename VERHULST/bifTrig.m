function bifTrig(N)

hold on
r=0:0.001:4;
x=0.1;
xlabel('$r$','Interpreter','latex');
ylabel('$x$','Interpreter','latex');
for i=1:N
    x = r.*sin(x);
end
for i=1:100
    x = r.*sin(x);
    plot(r,x,'.','MarkerSize',2);
end
end
   