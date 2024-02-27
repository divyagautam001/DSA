struct Emp {
Int id;
Int start;
Int end;
}

class EmployeeWork {

private:

	Int maxLimit = 10000;
	vector<int> temp(maxLimit, 0);

public:
	
	vector<int> daysWithAllEmployees( vector<Emp*> people) {
		Int peopleCount = people.size();
		vector<int> ans;
		for( auto it: people) {
			temp[ it->start ] = 1;
			temp[ it->end ] = -1;
        }
      for( int itr=1; itr<temp.size(); itr++ ) {
           temp[itr] = temp[itr] + temp[itr-1];
       }
    for( int itr=0; itr<temp.size(); itr++ ) {
           if(temp[itr] == peopleCount)
	       ans.push_back(itr);
     }
     return ans;
 }
vector daysWithAtLeastPEmployees( int p ) {
vector ans;
for( int itr=0; itr<temp.size(); itr++ ) {
if(temp[itr] >= p)
ans.push_back(itr);
}
return ans;
}

vector<pair<int,int>> consecutivePeriodsWithPEmployees( int p, int x ) {
Int i = 0;
Int j = 0;
vector<pair<int,int>> ans;
while( j<temp.size() ) {
if(temp[j] != p){
i++;
j++;
}
Else {
while(j<temp.size() && temp[j] == p){
j++;
}
Int period = j-i+1;
if(period >= x){
ans.push_back({i,j});
}
i=j;
}

}
return ans;
}
}
