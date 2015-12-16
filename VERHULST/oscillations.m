%default: for Verhulst equation
function oscillations(r,N)

hold on
x=zeros(1,N);
x(1)=0.2;
for i=1:N
    x(i+1) = x(i) + r.*x(i).*(1-x(i)); %change equation 
end
plot(1:N+1,x);
xlabel('N, number of iterations');
ylabel('x');