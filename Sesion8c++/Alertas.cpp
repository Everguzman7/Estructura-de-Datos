#include <iostream>
#include <queue>
#include <mutex>
#include <thread>
using namespace std;
 
queue<string> alertas;
mutex mtx;
 
void generarAlertas() {
    string eventos[] = {"Temperatura alta", "Aceite bajo", "Nivel de agua crítico"};
    for (string alerta : eventos) {
        this_thread::sleep_for(chrono::milliseconds(400));
        lock_guard<mutex> lock(mtx);
        alertas.push(alerta);
    }
}
 
void atenderAlertas() {
    for (int i = 0; i < 3; ++i) {
        this_thread::sleep_for(chrono::milliseconds(600));
        lock_guard<mutex> lock(mtx);
        if (!alertas.empty()) {
            cout << "Atendiendo: " << alertas.front() << endl;
            alertas.pop();
        }
    }
}
 
int main() {
    thread sensores(generarAlertas);
    thread tecnicos(atenderAlertas);
    sensores.join();
    tecnicos.join();
    return 0;
}
