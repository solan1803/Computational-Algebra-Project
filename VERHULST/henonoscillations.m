function henonoscillations(a,b,N)

hold on
x=zeros(1,N);
y=zeros(1,N);
x(1)=0.2;
for i=1:N
    x(i+1)=1-a.*(x(i)).^(2)+y(i);
y(i+1)=b.*x(i); %change equation 
end
plot(1:N+1,x);
xlabel('N, number of iterations');
ylabel('x');