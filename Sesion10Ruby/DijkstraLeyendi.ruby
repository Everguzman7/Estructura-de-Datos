require 'csv'
require 'set'
 
def cargar_grafo(archivo)
  grafo = Hash.new { |h, k| h[k] = {} }
  CSV.foreach(archivo, headers: true) do |row|
    origen, destino, peso = row['Origen'], row['Destino'], row['Peso'].to_i
    grafo[origen][destino] = peso
    grafo[origen][destino] = peso
  end
  grafo
end
 
def dijkstra(grafo, inicio)
  dist = Hash.new(Float::INFINITY)
  dist[inicio] = 0
  visitados = Set.new
 
  until visitados.size == grafo.size
    actual = dist.reject { |nodo, _| visitados.include?(nodo) }.min_by(&:last)&.first
    break unless actual
    visitados.add(actual)
 
    grafo[actual].each do |vecino, peso|
      if dist[actual] + peso < dist[vecino]
        dist[vecino] = dist[actual] + peso
      end
    end
  end
 
  dist
end
 
grafo = cargar_grafo('grafo_dijkstra.csv')
puts dijkstra(grafo, 'Centro')

   

