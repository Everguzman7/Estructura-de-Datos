#include <iostream>
#include <fstream>
#include <stack>
#include <sstream>
#include <string>

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

struct Registro
{
    string hora;
    double consumo;

    // Metodo Constructor para inicializar los atributos
    Registro(string h, double c) : hora(h), consumo(c) {}
};

// Función para leer los registros desde un archivo
void leerArchivo(const string &nombreArchivo, stack<Registro> &pila)
{
    ifstream archivo(nombreArchivo);
    if (!archivo)
    {
        cerr << "No se pudo abrir el archivo." << endl;
        return;
    }

    string linea;
    string hora;
    double consumo;

    while (getline(archivo, linea))
    {
        stringstream ss(linea);
        ss >> hora >> consumo;
        pila.push(Registro(hora, consumo));

        // Verificamos si el consumo supera los 550 kW y mostramos un mensaje
        if (consumo > 550.0)
        {
            cout << "¡Alerta! Consumo superior a 550kW: " << hora << " - " << consumo << " kW" << endl;
        }
    }

    archivo.close();
}

// Función para mostrar los registros en orden inverso
void mostrarRegistros(const stack<Registro> &pila)
{
    stack<Registro> copiaPila = pila;

    cout << "Registros en orden inverso:" << endl;
    while (!copiaPila.empty())
    {
        Registro r = copiaPila.top();
        cout << r.hora << " - " << r.consumo << " kW" << endl;
        copiaPila.pop();
    }
}

int main()
{
    imprimirEncabezado();

    // Crear una pila para almacenar los registros
    stack<Registro> pila;

    // Leer los registros desde el archivo y mostrarlos
    string nombreArchivo = "consumo.txt";
    leerArchivo(nombreArchivo, pila);
    mostrarRegistros(pila);

    return 0;
}
