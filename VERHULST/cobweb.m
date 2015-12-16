function cobweb(x0,r,N)

clf
x = 0:0.001:1.5;
y = x + r*x.*(1-x);
xlabel('$x_k$','Interpreter','latex');
ylabel('$x_{k+1}$','Interpreter','latex');

hold on;
plot(x,y,'b');
plot(x,x,'k');
x(1)=x0;
for i=1:N
    x(i+1) = x(i) + r*(x(i))*(1-x(i));
    if i==1 
        plot([x(i) x(i)], [0 x(i+1)], 'm');
        plot([x(i) x(i+1)], [x(i+1) x(i+1)], 'm');
    else
        plot([x(i) x(i)], [x(i) x(i+1)], 'm');
        plot([x(i) x(i+1)], [x(i+1) x(i+1)], 'm');
    end
end
hold off;