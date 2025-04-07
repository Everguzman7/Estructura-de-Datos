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
 
class Ciudad {
    int nodos;
    vector<vector<int>> adyacente;
public:
    Ciudad(int n) {
        nodos = n;
        adyacente.resize(n);
    }
    void conectar(int u, int v) {
        adyacente[u].push_back(v);
        adyacente[v].push_back(u);
    }
    void mostrar() {
        for (int i = 0; i < nodos; i++) {
            cout << "Intersección " << i << ": ";
            for (int j : adyacente[i])
                cout << j << " ";
            cout << endl;
        }
    }
};
 
int main() {
    imprimirEncabezado();
    Ciudad c(4);
    c.conectar(0, 1);
    c.conectar(0, 2);
    c.conectar(1, 3);
 
    cout << "Red de intersecciones:\n";
    c.mostrar();
    return 0;
}
