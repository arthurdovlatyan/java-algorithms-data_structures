
#include <iostream>
#include <vector>
#include <time.h>
#include <algorithm>

void fillVector(std::vector<std::string> storage,std::vector<short> &vect, int n){
    std::string str = storage.at(n);
    for (int i = 0; i < str.length(); ++i) {
        char a;
        a = str.at(i);
        int val = static_cast<int>(a) - static_cast<int>('0');
        vect.push_back(val);
    }
}
void addInVector(std::vector<std::string> &storage,std::vector<int> res){
    std::string str = "";
    for (int i = 0; i < res.size(); ++i) {
        str += res[i] + '0';
    }
    storage.push_back(str);
}
void sumUpTwoVectors(std::vector<short> &first,std::vector<short>& second,std::vector<int> &res){
    int k = 1;
    while (true){
        if (first.size() == 0 && second.size() == 0)
        {
            break;
        }
        if (first.empty()){
            if (k == 1) {
                int num = second.at(second.size() - 1) + res.at(res.size() - 1);
                second.pop_back();
                res[res.size() - 1] = num % 10;
                num = num / 10;
                k = 0;
                if (num > 0) {
                    res.push_back(num % 10);
                    num = num / 10;
                    k = 1;
                }
            } else{
                int num = second.at(second.size() - 1);
                second.pop_back();
                res.push_back(num % 10);
                num = num / 10;
                k = 0;
                if (num > 0) {
                    res.push_back(num % 10);
                    num = num / 10;
                    k = 1;
                }
            }
        } else if (second.empty()){
            if (k == 1) {
                int num = first.at(first.size() - 1) + res.at(res.size() - 1);
                first.pop_back();
                res[res.size() - 1] = num % 10;
                num = num / 10;
                k = 0;
                if (num > 0) {
                    res.push_back(num % 10);
                    num = num / 10;
                    k = 1;
                }
            } else {
                int num = first.at(first.size() - 1);
                first.pop_back();
                res.push_back( num % 10);
                num = num / 10;
                if (num > 0) {
                    res.push_back(num % 10);
                    num = num / 10;
                    k = 1;
                }
            }
        } else{
            if (k == 1) {
                int num = first.at(first.size() - 1) + second.at(second.size() - 1) + res.at(res.size() - 1);
                first.pop_back();
                second.pop_back();
                res[res.size() - 1] = num % 10;
                num = num / 10;
                k = 0;
                if (num > 0) {
                    res.push_back(num % 10);
                    k = 1;
                }
            } else {
                int num = first.at(first.size() - 1) + second.at(second.size() - 1);
                first.pop_back();
                second.pop_back();
                res.push_back(num % 10);
                num = num / 10;
                if (num > 0) {
                    res.push_back(num % 10);
                    k = 1;
                }
            }
        }
    }
    std::reverse(res.begin(),res.end());
}
std::string highestFib3()
{
    std::vector<std::string> storage;
    std::vector<short> first;
    std::vector<short> second;
    storage.push_back("0");
    storage.push_back("1");
    int i = 2;
    while(i < 2000){
        clock_t start = clock();
        fillVector(storage,first,i - 1);
        fillVector(storage,second,i-2);
        std::vector<int> res;
        res.push_back(0);
        sumUpTwoVectors(first,second,res);
        addInVector(storage,res);
        double time = (double) ((clock() - start)/ CLOCKS_PER_SEC);
        std::cout << i - 1 << "   "<< storage.at(i - 1) << std::endl;
        if ( time > 5 ) {
            std::cout << "This program is not meant to warm a house so it will terminate here" <<std::endl;
            break;
        }
        first.clear();
        second.clear();
        res.clear();
        ++i;
    }
}

int main()
{
    highestFib3();
    return 0;
}

