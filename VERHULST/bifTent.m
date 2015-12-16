function bifTent(N)

hold on
r=0:0.001:2;
x=0.5;
xlabel('$r$','Interpreter','latex');
ylabel('$x$','Interpreter','latex');
for i=1:N
    x = r.*(0.5-(abs(0.5-x)));
end
for i=1:100
    x = r.*(0.5-abs(0.5-x));
    plot(r,x,'.','MarkerSize',2);
end



 
 
 

