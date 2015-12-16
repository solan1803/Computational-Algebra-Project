%calculates the r values at the period doubling points
function [i] = rVerhulst(N)

clf
hold on
r=0.001:0.001:3;
x=0.1;
X=zeros(50,length(r));
xlabel('$r$','Interpreter','latex');
ylabel('$x$','Interpreter','latex');
for i=1:N
    x = x + r.*x.*(1-x);
end
for i=1:250
    x = x + r.*x.*(1-x);
    X(i,:)=x; %matrix of the plotted entries against r
    plot(r,x,'.','MarkerSize',2);
end

 
a = X(end,:); %takes the last row of X i.e. the last entry of each r
[~,j]=ind2sub(size(a),1:numel(a)); 
b=a.';
dlmwrite('rVerhulst.txt',[j'/1000 b(:)],'delimiter', '\t'); %writes the x values to a text for referencing
for i=2540:2580 %change i to the range of r values you want to inspect
    if abs(b(i)-b(i-1))>0.001 && abs(b(i)-b(i-2))<0.01
        i=i/1000; 
        break;
    end
end
