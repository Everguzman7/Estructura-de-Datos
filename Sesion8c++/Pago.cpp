#include <iostream>
#include <queue>
#include <string>
using namespace std;
 
struct Pago {
    string empleado;
    int prioridad; // 3 = contrato fijo, 2 = temporal, 1 = externo
    bool operator<(const Pago& otro) const {
        return prioridad < otro.prioridad;
    }
};
 
int main() {
    priority_queue<Pago> nomina;
    nomina.push({"Ana", 1});
    nomina.push({"Carlos", 3});
    nomina.push({"Luis", 2});
 
    while (!nomina.empty()) {
        cout << "Procesando pago a: " << nomina.top().empleado << endl;
        nomina.pop();
    }
    return 0;
}
