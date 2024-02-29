#include<bits/stdc++.h>
using namespace std;

int main() {
    int arr[5] = {7, 4, 5, 6, 5};
    int n = sizeof(arr)/sizeof(arr[0]);
    int sum = 0;

    for(int i = 0 ; i < n ; i++) {
        sum += arr[i];
    }

    for(int i = 0 ; i < n - 1;)
    {
        int j = i + 1;

        int diff = arr[j] - arr[i];

        if(diff == -1 || diff == 1)
        {
            while(j < n && (arr[j] - arr[j-1] == diff)) {
                j++;
            }
            cout<<"Subarray: "<<i<<" "<<j-1<<endl;
            int subSize = j - i;

            int fn = subSize - 1;
            int diff = subSize - 2;
            for(int k = i ; k <= j - 1 ; k++) {
                sum += arr[k]*fn;
                fn += diff;
                diff -= 2;
            }

            i = j - 1;
        }

        else i++;
    }

    cout<<sum<<endl;

    return 0;
}
