function [i] = rLogistic(N)

clf
hold on
r=0.001:0.001:4;
x=0.1;
X=zeros(50,length(r));
xlabel('$r$','Interpreter','latex');
ylabel('$x$','Interpreter','latex');
for i=1:N
    x = r.*x.*(1-x);
end
for i=1:250
    x = r.*x.*(1-x);
    X(i,:)=x;
    plot(r,x,'.','MarkerSize',2);
end

 
a = X(end,:);
[~,j]=ind2sub(size(a),1:numel(a));
b=a.';
dlmwrite('rLogistic.txt',[j'/1000 b(:)],'delimiter', '\t');
for i=3500:3600
    if b(i+1)<b(i) && b(i)>b(i-1) || abs(b(i)-b(i-1))>0.005
        i=i/1000;
        break;
    end
end
