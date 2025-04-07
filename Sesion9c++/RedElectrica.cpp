#include <iostream>
#include <vector>
using namespace std;

void imprimirEncabezado() {
    string nombre = "Ever Rodriguez";
    string campus = "Campus Cali, U. Cooperativa de Colombia";
    string repositorioGit = " ";

    // Obtener la fecha y hora actual
    time_t ahora = time(0);
    tm *ltm = localtime(&ahora);
    char fechaHora[20];
    strftime(fechaHora, sizeof(fechaHora), "%d/%m/%Y %H:%M:%S", ltm);

    // Imprimir el encabezado
    cout << "+----------------------------------------" << endl;
    cout << "| 👤 Nombre: " << nombre << endl;
    cout << "| 🎓 Campus: " << campus << endl;
    cout << "| 📅 Fecha y hora: " << fechaHora << endl;
    cout << "| 📂 Repositorio Git: " << repositorioGit << endl;
    cout << "+----------------------------------------" << endl;
    cout << endl;
}
 
class Red {
    int nodos;
    vector<vector<int>> adyacente;
public:
    Red(int n) {
        nodos = n;
        adyacente.resize(n);
    }
    void conectar(int origen, int destino) {
        adyacente[origen].push_back(destino);
    }
    void mostrar() {
        for (int i = 0; i < nodos; i++) {
            cout << "Nodo " << i << " -> ";
            for (int j : adyacente[i])
                cout << j << " ";
            cout << endl;
        }
    }
};
 
int main() {
    imprimirEncabezado();
    Red red(5);
    red.conectar(0, 1);
    red.conectar(0, 2);
    red.conectar(1, 3);
    red.conectar(2, 3);
    red.conectar(3, 4);
 
    cout << "Conexiones eléctricas:\n";
    red.mostrar();
    return 0;
}
