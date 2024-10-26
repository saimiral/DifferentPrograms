import copy
import sys 
  
sys.setrecursionlimit(10**6)

def find_solution(front,queue,closed,goal,method): #εύρεση λύσης    
    if not front: #κενό μέτωπο
        print('\n Δεν βρέθηκε λύση \n')
    elif front[0] in closed: #έχουμε ήδη επισκεφθεί αυτή τη κατάσταση
        
        front.pop(0)
        queue.pop(0)
        
        find_solution(front, queue, closed, goal, method) #αναδρομική κλήση της συνάρτησης
    elif front[0] == goal:
        print('\n Βρέθηκε η λύση \n')
        print('Final Front: ') 
        print(front[0])
        print('\nFinal Queue: ') 
        print(queue[0])
    else:   
        closed.append(front[0]) #προσθήκη της κατάστασης στη λίστα με αυτές που έχει επισκευθεί
        front_children=expand_front(front, method) #κλήση συνάρτησης για επέκταση μετώπου
        
        queue_children=extend_queue(queue, method) #κλήση συνάρτησης για επέκταση ουράς
                
        find_solution(front_children, queue_children, closed, goal, method)  #αναδρομική κλήση της συνάρτησης

def make_front(state): #αρχικοποίηση μετώπου
    return [state]
    
def make_queue(state): #αρχικοποίηση ουράς
    return [[state]]

def expand_front(front,method): #επέκταση μετώπου
    if method == 'dfs':
        if front:
            print("\n Front: \n")
            print(front)
            node=front.pop(0)
            for child in find_children(node):
                front.insert(0, child)
    elif method == 'bfs':
        if front:
            print("\n Front:\n ")
            print(front)
            node=front.pop(0)
            for child in find_children(node):
                front.insert(-1, child)
    elif method == "bestfs":
       if front:
        print("\n Front: \n")
        print(front)
        node = front.pop(0)
        for child in find_children(node):
            front.append(child)
            front.sort(key=lambda x: heuristic(x))
           
    return front

def extend_queue(queue, method): #επέκταση ουράς
    if method == 'dfs':
            print("\n Queue:\n")
            print(queue)
            node=queue.pop(0)
            queue_copy=copy.deepcopy(queue)
            for child in find_children(node[-1]):
                path=copy.deepcopy(node)
                path.append(child)
                
                queue_copy.insert(0, path)
    elif method == 'bfs':
            print("\n Queue:\n")
            print(queue)
            node=queue.pop(0)
            queue_copy=copy.deepcopy(queue)
            for child in find_children(node[-1]):
                path=copy.deepcopy(node)
                path.append(child)
                
                queue_copy.insert(-1, path)
    else:  # method == 'bestfs'
        print("\n Queue:\n")
        print(queue)
        queue_copy = copy.deepcopy(queue)
        node = queue_copy.pop(0)

        for child in find_children(node[-1]):
            path = copy.deepcopy(node)
            path.append(child)

            queue_copy.append(path)
            
            #Ταξινόμηση της ουράς βάσει ευριστικού κριτηρίου(κριτήριο είναι το πλήθος ενοίκων στους 4 ορόφους)
            queue_copy.sort(key=lambda x: heuristic(x[-1]))   
            
    return queue_copy

def heuristic(state):
    people_total = state[1] + state[2] + state[3] + state[4]
    return people_total

def find_children(state): #εύρεση παιδιών
    child = []
 
    
    floor1_child = go_to_floor1(state) #εύρεση παιδιού από 1ο όροφο
    floor2_child = go_to_floor2(state) #                   2ο όροφο
    floor3_child = go_to_floor3(state) #                   3ο όροφο
    floor4_child = go_to_floor4(state) #                   4ο όροφο
    roof_child = go_to_roof(state)       #                   ταράτσα
    
    if floor1_child != None:  #αν υπάρχει παιδί
        child.append(floor1_child)
        
    if floor2_child != None:  #αν υπάρχει παιδί
        child.append(floor2_child)
        
    if floor3_child != None:  #αν υπάρχει παιδί
        child.append(floor3_child)
        
    if floor4_child != None:  #αν υπάρχει παιδί
        child.append(floor4_child)
        
    if roof_child != None:  #αν υπάρχει παιδί
        child.append(roof_child)    
    
    return child
   
def go_to_floor1(state): #μετακίνηση στον 1ο όροφο
    if state[5] < 8 and state[1] > 0:
        if state[1] > 8 - state[5]:    #να μπούν στο ασανσέρ όσοι χρειάζεται για να γεμίσει (να έχει 8 ενοίκους) 
    	    new_state = [1] + [state[1] + state[5] - 8] + [state[2]] + [state[3]] + [state[4]] + [8]
        else:    #να μπούν στο ασανσέρ όσοι έχουν απομείνει στον όροφο αυτό
    	    new_state = [1] + [0] + [state[2]] + [state[3]] + [state[4]] + [state[5] + state[1]]
        return new_state

def go_to_floor2(state): #μετακίνηση στον 2ο όροφο
    if state[5] < 8 and state[2] > 0:
        if state[2] > 8 - state[5]:
            new_state = [2] + [state[1]] + [state[2] + state[5] - 8] + [state[3]] + [state[4]] + [8]
        else:
            new_state = [2] + [state[1]] + [0] + [state[3]] + [state[4]] + [state[5] + state[2]]
        return new_state

def go_to_floor3(state): #μετακίνηση στον 3ο όροφο
    if state[5] < 8 and state[3] > 0:
        if state[3] > 8 - state[5]:
            new_state = [3] + [state[1]] + [state[2]] + [state[3] + state[5] - 8] + [state[4]] + [8]
        else:
            new_state = [3] + [state[1]] + [state[2]] + [0] + [state[4]] + [state[5] + state[3]]
        return new_state

def go_to_floor4(state): #μετακίνηση στον 4ο όροφο
    if state[5] < 8 and state[4] > 0: 
        if state[4] > 8 - state[5]:
            new_state = [4] + [state[1]] + [state[2]] + [state[3]] + [state[4] + state[5] - 8] + [8]
        else:
            new_state = [4] + [state[1]] + [state[2]] + [state[3]] + [0] + [state[5] + state[4]]
        return new_state   

def go_to_roof(state): #μετακίνηση στην ταράτσα σε περίπτωση που το ασανσέρ έχει γεμίσει
    if state[5] == 8:
        new_state = [5] + [state[1]] + [state[2]] + [state[3]] + [state[4]] + [0]
        return new_state
    
def main():
    state = [0,9,4,12,7,0] #αρχική κατάσταση 
    #[οροφός ασανσέρ,ένοικοι στον 1ο, ένοικοι στον 2ο, ένοικοι στον 3ο, ένοικοι στον 4ο, ένοικοι στο ασανσέρ]
    goal = [5,0,0,0,0,0] #τελική κατάσταση
    method = input('Ποιόν αλγόριθμο θέλετε να χρησιμοποιήσετε; (dfs,bfs,bestfs)\n')
    print('Ξεκινάμε την επίλυση του προβλήματος με ' + method + '\n')
    find_solution(make_front(state),make_queue(state),[],goal,method)
    
if __name__ == "__main__":
    main()